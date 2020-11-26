-- Create superuser user
CREATE ROLE banshee_owner WITH LOGIN PASSWORD $1;
ALTER USER banshee_owner WITH SUPERUSER;

-- Create readonly user
CREATE ROLE read_only WITH LOGIN PASSWORD $2;
GRANT CONNECT ON DATABASE "Banshee" TO read_only;
GRANT USAGE ON SCHEMA public TO read_only;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO read_only;
GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO read_only;