# DEMO-201 Execution Report

## Live browser verification

- Registration: completed successfully at the Tricentis Demo Web Shop.
- Login: after logging out, login succeeded and returned to the home page.
- Authenticated header: displayed the account email and logout link.
- Invalid password: displayed the expected unsuccessful-login summary and
  incorrect-credentials detail.
- Invalid email: displayed `Please enter a valid email address.`

## Repository test execution

The new Selenium/TestNG scenarios are implemented but the Maven suite was not
executed in this environment: the command runner could not start because neither
`pwsh.exe` nor `powershell.exe` is available. No automated Maven pass/fail
results are claimed.

## Credentials

No account password or other secret is included in this report or in source
control.
