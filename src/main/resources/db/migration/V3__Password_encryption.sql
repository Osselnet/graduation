/*
These passwords were encrypted there:
https://www.browserling.com/tools/bcrypt
*/
UPDATE users
SET password = CASE name
                   WHEN 'admin'
                       THEN /*admin*/'$2a$08$onJmizwAA2IrJbm55ExE8uZUu6dohWbSpildyzsGjHyfKR2Ips12q'
                   WHEN 'user'
                       THEN /*password*/'$2a$08$55qUQx4SMyfNcvbH7q.rCeVwHvmyF5y63.y/.nwc3.PM93ms.6mou'
                   WHEN 'user2'
                       THEN /*password*/'$2a$08$k6i8.TWY/aK5XtokR1ualedRiSlCPmpbBxH9Tzps2VSUMxHkokcLm' END
WHERE name IN ('admin', 'user', 'user2');