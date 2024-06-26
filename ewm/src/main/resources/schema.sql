drop table if exists users, categories, locations, events, participation_requests, compilations;

CREATE TABLE IF NOT EXISTS users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL primary key,
  email varchar(254) NOT NULL,
  name varchar(250) NOT NULL,
  CONSTRAINT UQ_USER_NAME UNIQUE (name)
);

CREATE TABLE IF NOT exists categories (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL primary key,
  name varchar(50) NOT NULL,
  CONSTRAINT UQ_CATEGORY_NAME UNIQUE (name)
);

CREATE TABLE IF NOT exists locations (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL primary key,
  lat float NOT NULL,
  lon float NOT NULL
);

CREATE TABLE IF NOT EXISTS events (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL primary key,
  annotation varchar(2000) NOT NULL,
  category_id BIGINT references categories(id),
  description varchar(7000) NOT NULL,
  event_date timestamp WITHOUT TIME ZONE NOT NULL,
  initiator_id BIGINT references users(id),
  location_id BIGINT references locations(id) on delete cascade,
  paid boolean NOT NULL,
  participant_limit int,
  confirmed_requests int,
  available boolean,
  published_on timestamp WITHOUT TIME ZONE,
  request_moderation boolean,
  state varchar,
  state_action varchar,
  title varchar(120) NOT NULL,
  created_on timestamp WITHOUT TIME ZONE,
  views int
);

CREATE TABLE IF NOT EXISTS participation_requests (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL primary key,
  created timestamp WITHOUT TIME ZONE,
  event_id BIGINT references events(id),
  requester_id BIGINT references users(id) on delete cascade,
  status varchar,
  UNIQUE (event_id, requester_id)
);

CREATE TABLE IF NOT EXISTS compilations (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL primary key,
  pinned boolean,
  title varchar(50) NOT NULL,
  events jsonb
);
