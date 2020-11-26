CREATE ROLE banshee_owner WITH LOGIN PASSWORD $1;
-- GRANT rds_superuser to banshee_owner;
ALTER USER banshee_owner WITH SUPERUSER;

CREATE ROLE read_only WITH LOGIN PASSWORD $2;
GRANT CONNECT ON DATABASE "Banshee" TO read_only;
GRANT USAGE ON SCHEMA public TO read_only;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO read_only;
GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO read_only;