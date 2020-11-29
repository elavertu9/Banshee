# Banshee Database Usage

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [Postgres](#postgres)
  * [Install Debian Linux](#install-debian-linux)
* [Database Manager](#database-manager)

## Postgres
#### Install Debian Linux
1. Install
```text
sudo apt-get install postgresql
```
2. Setup user
```text
# Enter PSQL
sudo -u postgres psql postgres
# Once in
\password postgres
# Set your password, easiest if you use "password" for local testing config
```
3. Create Database. While logged into PSQL
```text
CREATE DATABASE "Banshee";
\c "Banshee";
\i '/path/to/Banshee/database/create_all_db_resources.sql'
```

## Database Manager
* [DBeaver](https://dbeaver.io/download/)
* Connect to postgres database configured above