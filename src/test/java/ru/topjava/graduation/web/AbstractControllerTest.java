package ru.topjava.graduation.web;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.topjava.graduation.TestMatchers;
import ru.topjava.graduation.model.AbstractBaseEntity;
import ru.topjava.graduation.model.User;
import ru.topjava.graduation.web.json.JsonUtil;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.topjava.graduation.TestUtil.readFromJson;
import static ru.topjava.graduation.TestUtil.readFromJsonMvcResult;
import static ru.topjava.graduation.data.UserTestData.ADMIN;
import static ru.topjava.graduation.web.AbstractControllerTest.RequestWrapper.wrap;
import static ru.topjava.graduation.web.Controller.JSON_TYPE;

@Transactional
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
abstract public class AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String url;

    public AbstractControllerTest(String url) {
        this.url = url;
    }

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    protected <T> void createNew(AbstractBaseEntity entity, TestMatchers<T> matchers) throws Exception {
        ResultActions action = perform(doPost().jsonBody(entity).basicAuth(ADMIN));
        AbstractBaseEntity created = readFromJson(action, entity.getClass());
        Integer newId = created.getId();
        entity.setId(newId);
        matchers.assertMatch(created, entity);
    }

    protected void deleteAndCheck(Integer id) throws Exception {
        expectNoContent(perform(doDelete(id).basicAuth(ADMIN)));
        expectNotFound(perform(doGet(id).basicAuth(ADMIN)));
    }

    protected <T> void getOne(AbstractBaseEntity entity, TestMatchers<T> matchers) throws Exception {
        perform(doGet(entity.getId()).basicAuth(ADMIN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_TYPE))
                .andExpect(result -> matchers.assertMatch(readFromJsonMvcResult(result, entity.getClass()), entity));
    }

    protected <T> void updateExisted(AbstractBaseEntity entity, TestMatchers<T> matchers) throws Exception {
        ResultActions action = perform(doPost().jsonBody(entity).basicAuth(ADMIN));
        AbstractBaseEntity updated = readFromJson(action, entity.getClass());
        matchers.assertMatch(updated, entity);
    }

    protected <T> void getAllEntities(ResultActions resultActions, List<T> entityList, TestMatchers<T> matchers) throws Exception {
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_TYPE))
                .andExpect(matchers.contentJson(entityList));
    }

    protected void expectForbidden(ResultActions perform) throws Exception {
        perform.andDo(print()).andExpect(status().isForbidden());
    }

    protected void expectUnauthorized(ResultActions perform) throws Exception {
        perform.andDo(print()).andExpect(status().isUnauthorized());
    }

    protected void expectNotFound(ResultActions perform) throws Exception {
        perform.andDo(print()).andExpect(status().isNotFound());
    }

    protected void expectDuplicated(ResultActions perform) throws Exception {
        perform.andDo(print()).andExpect(status().isUnprocessableEntity());
    }

    protected void expectNoContent(ResultActions perform) throws Exception {
        perform.andDo(print()).andExpect(status().isNoContent());
    }

    protected void expectNotAllowed(ResultActions perform) throws Exception {
        perform.andDo(print()).andExpect(status().isMethodNotAllowed());
    }

    public ResultActions perform(RequestWrapper wrapper) throws Exception {
        return perform(wrapper.builder);
    }

    public ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    protected RequestWrapper doGet() {
        return wrap(MockMvcRequestBuilders.get(url));
    }

    protected RequestWrapper doGet(String url) {
        return wrap(MockMvcRequestBuilders.get(url));
    }

    protected RequestWrapper doGet(int id) {
        return wrap(MockMvcRequestBuilders.get(url + "/{id}", id));
    }

    protected RequestWrapper doDelete() {
        return wrap(MockMvcRequestBuilders.delete(url));
    }

    protected RequestWrapper doDelete(int id) {
        return wrap(MockMvcRequestBuilders.delete(url + "/{id}", id));
    }

    protected RequestWrapper doPost() {
        return wrap(MockMvcRequestBuilders.post(url).contentType(JSON_TYPE));
    }

    public static class RequestWrapper {
        private final MockHttpServletRequestBuilder builder;

        private RequestWrapper(MockHttpServletRequestBuilder builder) {
            this.builder = builder;
        }

        public static RequestWrapper wrap(MockHttpServletRequestBuilder builder) {
            return new RequestWrapper(builder);
        }

        public <T> RequestWrapper jsonBody(T body) {
            builder.contentType(MediaType.APPLICATION_JSON).content(JsonUtil.writeValue(body));
            return this;
        }

        public RequestWrapper basicAuth(User user) {
            builder.with(SecurityMockMvcRequestPostProcessors.httpBasic(user.getName(), user.getPassword()));
            return this;
        }
    }
}