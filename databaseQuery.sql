create database hirenseeks;
use hirenseeks;

drop table user;

select * from user;

CREATE TABLE user (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    password VARCHAR(128) NOT NULL,
    lastLogin DATETIME(6) NULL,
    isSuperuser TINYINT(1) NOT NULL DEFAULT 0,
    userName VARCHAR(100) NOT NULL UNIQUE,
    compName VARCHAR(50) DEFAULT '',
    firstName VARCHAR(50) DEFAULT '',
    lastName VARCHAR(50) DEFAULT '',
    email VARCHAR(70) NOT NULL UNIQUE,
    bio VARCHAR(300) DEFAULT '',
    signUpDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    isStaff BOOLEAN NOT NULL DEFAULT false,
    isActive BOOLEAN NOT NULL DEFAULT true,
    isRecruiter BOOLEAN NOT NULL DEFAULT false,
    countryCode VARCHAR(5) DEFAULT '',
    contactNumber VARCHAR(10) DEFAULT '',
    dob DATE NULL,
    address VARCHAR(100) DEFAULT '',
    city VARCHAR(50) DEFAULT '',
    state VARCHAR(50) DEFAULT '',
    country VARCHAR(50) DEFAULT '',
    skills LONGTEXT ,
    projects LONGTEXT ,
    linkGithub LONGTEXT,
    linkLinkedIn LONGTEXT,
    linkExtra LONGTEXT ,
    appliedJobsTo LONGTEXT 
);


-- Allow NULL for specific columns and set default values to NULL
ALTER TABLE user
    MODIFY COLUMN appliedJobsTo LONGTEXT DEFAULT NULL,
    MODIFY COLUMN linkExtra LONGTEXT DEFAULT NULL,
    MODIFY COLUMN projects LONGTEXT DEFAULT NULL,
    MODIFY COLUMN skills LONGTEXT DEFAULT NULL;

ALTER TABLE user
    MODIFY COLUMN isStaff TINYINT DEFAULT 0,
    MODIFY COLUMN isActive TINYINT DEFAULT 1,
    MODIFY COLUMN isRecruiter TINYINT DEFAULT 0,
    MODIFY COLUMN isSuperuser TINYINT DEFAULT 0,
    MODIFY COLUMN signUpDate DATE DEFAULT (CURRENT_DATE);

INSERT INTO user (
    password, lastLogin, isSuperuser, userName, compName, firstName, lastName,
    email, bio, signUpDate, isStaff, isActive, isRecruiter, countryCode,
    contactNumber, dob, address, city, state, country,
    skills, projects, linkGithub, linkLinkedIn, linkExtra, appliedJobsTo
)
VALUES
(
    'samplePassword1', '2023-12-01 12:30:00', 1, 'user1', 'Company1', 'John', 'Doe',
    'user1@example.com', 'Sample bio for user1', '2023-12-01', 1, 1, 0, 'US',
    '1234567890', '1990-01-01', '123 Main St', 'City1', 'State1', 'Country1',
    'Java, SQL, HTML', 'Project1, Project2', 'https://github.com/user1', 'https://linkedin.com/user1', 'Additional Link1', 'Job1, Job2'
),
(
    'samplePassword2', '2023-12-02 14:45:00', 0, 'user2', 'Company2', 'Jane', 'Smith',
    'user2@example.com', 'Sample bio for user2', '2023-12-02', 0, 1, 1, 'UK',
    '9876543210', '1985-05-15', '456 Oak St', 'City2', 'State2', 'Country2',
    'Python, JavaScript, CSS', 'Project3, Project4', 'https://github.com/user2', 'https://linkedin.com/user2', 'Additional Link2', 'Job3, Job4'
);
