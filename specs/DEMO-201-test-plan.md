# DEMO-201 Test Plan

## Scope

Validate customer registration and login at `https://demowebshop.tricentis.com/`,
plus the negative-login checks listed in `reports/EndToEnd.md`. The repository
uses Selenium, Java, and TestNG, so the executable test is implemented in that
framework rather than as a standalone Playwright spec.

## Scenarios

1. Register a customer if the configured email is new; accept only the site's
   duplicate-email response if it already exists. Log out and log in with the
   configured account. Verify the home-page URL, account email, and logout link.
2. Submit the configured email with an invalid password. Verify the documented
   failure summary and incorrect-credentials detail.
3. Submit an invalid email format. Verify the field-level email validation
   message.

## Test data and execution

The registration and invalid-password scenarios read credentials from
`DEMOWEBSHOP_EMAIL`, `DEMOWEBSHOP_PASSWORD`, `DEMOWEBSHOP_FIRST_NAME`, and
`DEMOWEBSHOP_LAST_NAME`. These values must be provided in the test process
environment and are not stored in the repository. Scenarios requiring missing
values are reported as skipped. The invalid-email scenario needs no account
credentials.

Run the Maven TestNG suite with `mvn test` after setting the required
environment variables. Browser selection and headless mode remain controlled
by the existing project configuration.

## Executable test

`src/test/java/com/demowebshop/tests/RegistrationLoginTest.java`
