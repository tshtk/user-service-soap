CREATE TABLE IF NOT EXISTS roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    login VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_login VARCHAR NOT NULL references users(login),
    role_id BIGINT NOT NULL references roles(id),

    CONSTRAINT login_to_user UNIQUE (user_login, role_id)
);



