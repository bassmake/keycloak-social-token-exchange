# How to use keycloak to validate google+ tokens

* create realm `keycloak-poc`
* create `google` identity provider and set credentials from you google app there
* create `facebook` identity provider and set credentials from you google app there 
* create confidential client `social` with only direct access grant enabled 
* grant permissions to client: https://www.keycloak.org/docs/latest/securing_apps/index.html#_grant_permission_external_exchange
* https://www.keycloak.org/docs/latest/securing_apps/index.html#external-token-to-internal-token-exchange

## configuring First login flow

* https://www.keycloak.org/docs/latest/server_admin/index.html#_identity_broker_first_login
* https://www.keycloak.org/docs/latest/server_admin/index.html#_authentication-flows
