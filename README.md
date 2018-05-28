# How to use keycloak to validate google+ tokens

* create realm `keycloak-poc`
* create `google` identity provider and set credentials from you google app there 
* create confidential client `social` with only direct access grant enabled 
* grant permissions to client: https://www.keycloak.org/docs/latest/securing_apps/index.html#_grant_permission_external_exchange
* https://www.keycloak.org/docs/latest/securing_apps/index.html#external-token-to-internal-token-exchange
