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

ALTER TABLE user
    MODIFY COLUMN password LONGTEXT NOT NULL;

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


-- Job Table
use hirenseeks;

drop table hirenseeks.job;

CREATE TABLE job (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    jobDate datetime DEFAULT CURRENT_TIMESTAMP,
    title VARCHAR(50),
    jobPos VARCHAR(50),
    description VARCHAR(300),
    timing VARCHAR(50),
	reqSkill TEXT,
    expLevel VARCHAR(50),
    postedBy VARCHAR(50),
    location VARCHAR(50),
    appliedPeople TEXT
);

INSERT INTO job (jobDate, title, jobPos, description, timing, reqSkill, expLevel, postedBy, location)
VALUES
    ('2023-11-28 09:30:00', 'Software Engineer', 'SDE - 1', 'Exciting Software Engineer Opportunity', '8', '[\'Java\',\'Algorithm\',\'Computer Science\']', '2', 'Hacker00619', 'Remote'),
    ('2023-12-01 10:15:00', 'Frontend Developer', 'UI/UX Designer', 'Creative Frontend Developer Needed', '9', '[\'HTML\',\'CSS\',\'JavaScript\',\'React\']', '3', 'Hacker00619', 'San Francisco'),
    ('2023-12-03 11:00:00', 'Data Analyst', 'Data Science Intern', 'Data Analyst Internship Position', '20', '[\'Python\',\'Data Analysis\',\'Statistics\']', '1', 'jash', 'New York'),
    ('2023-12-05 11:45:00', 'Machine Learning Engineer', 'AI Specialist', 'Machine Learning Expert Wanted', '10', '[\'Python\',\'Machine Learning\',\'Deep Learning\']', '4', 'Hacker00619', 'London'),
    ('2023-12-08 12:30:00', 'Full Stack Developer', 'Senior Developer', 'Full Stack Developer Opening', '8', '[\'Java\',\'JavaScript\',\'Node.js\',\'MongoDB\']', '5', 'Hacker00619', 'Berlin'),
    ('2023-12-10 13:15:00', 'Network Administrator', 'Security Analyst', 'Network Security Specialist Position', '9', '[\'Networking\',\'Security\',\'Firewalls\']', '3', 'Hacker00619', 'Tokyo'),
    ('2023-12-12 14:00:00', 'Business Analyst', 'Market Researcher', 'Business Analyst Position Open', '8', '[\'Market Research\',\'Business Analysis\']', '2', 'jash', 'Singapore'),
    ('2023-12-15 14:45:00', 'iOS Developer', 'Mobile App Developer', 'iOS Developer Wanted', '10', '[\'Swift\',\'iOS\',\'Mobile Development\']', '4', 'Hacker00619', 'New Delhi'),
    ('2023-12-17 15:30:00', 'Cloud Architect', 'AWS Specialist', 'Cloud Architect Opening', '8', '[\'AWS\',\'Cloud Computing\',\'Architecture\']', '6', 'Hacker00619', 'Seattle'),
    ('2023-12-19 16:15:00', 'DevOps Engineer', 'Automation Specialist', 'DevOps Engineer Position', '9', '[\'Docker\',\'Kubernetes\',\'CI/CD\']', '4', 'Hacker00619', 'Sydney'),
    ('2023-12-22 17:00:00', 'Game Developer', 'Unity Programmer', 'Exciting Game Developer Role', '8', '[\'Unity\',\'Game Development\',\'C#\']', '3', 'jash', 'Los Angeles'),
    ('2023-12-24 17:45:00', 'Blockchain Developer', 'Smart Contract Developer', 'Blockchain Developer Needed', '9', '[\'Blockchain\',\'Smart Contracts\',\'Ethereum\']', '5', 'Hacker00619', 'Toronto'),
    ('2023-12-27 18:30:00', 'AI Researcher', 'Deep Learning Scientist', 'AI Researcher Position', '8', '[\'AI\',\'Machine Learning\',\'Research\']', '8', 'Hacker00619', 'Berlin'),
    ('2023-12-29 19:15:00', 'Computer Vision Engineer', 'Image Processing Specialist', 'Computer Vision Engineer Role', '9', '[\'Computer Vision\',\'Image Processing\']', '7', 'Hacker00619', 'Paris'),
    ('2024-01-01 20:00:00', 'Graphic Designer', 'UI/UX Artist', 'Graphic Designer Wanted', '8', '[\'Graphic Design\',\'UI/UX\',\'Adobe Creative Suite\']', '3', 'jash', 'Barcelona'),
    ('2024-01-03 20:45:00', 'Android Developer', 'Mobile App Developer', 'Android Developer Position', '10', '[\'Android\',\'Java\',\'Mobile Development\']', '5', 'Hacker00619', 'Mumbai'),
    ('2024-01-06 21:30:00', 'Software QA Engineer', 'Quality Assurance Specialist', 'QA Engineer Opening', '8', '[\'QA\',\'Testing\',\'Automated Testing\']', '4', 'Hacker00619', 'Singapore'),
    ('2024-01-08 22:15:00', 'UX Designer', 'User Experience Specialist', 'UX Designer Role', '9', '[\'UX\',\'User Experience\',\'Prototyping\']', '6', 'jash', 'San Francisco'),
    ('2024-01-11 23:00:00', 'Database Administrator', 'SQL Specialist', 'Database Administrator Position', '8', '[\'SQL\',\'Database\',\'Performance Tuning\']', '7', 'Hacker00619', 'London'),
    ('2024-01-13 23:45:00', 'IT Support Specialist', 'Help Desk Technician', 'IT Support Specialist Role', '9', '[\'IT Support\',\'Troubleshooting\',\'Network\']', '5', 'Hacker00619', 'New York'),
    ('2024-01-16 00:30:00', 'SEO Specialist', 'Search Engine Optimizer', 'SEO Specialist Position', '8', '[\'SEO\',\'Digital Marketing\',\'Keyword Research\']', '3', 'Hacker00619', 'Berlin'),
    ('2024-01-18 01:15:00', 'Technical Writer', 'Documentation Specialist', 'Technical Writer Role', '9', '[\'Technical Writing\',\'Documentation\',\'Communication\']', '5', 'jash', 'Tokyo'),
    ('2024-01-21 02:00:00', 'Network Engineer', 'Cisco Certified Specialist', 'Network Engineer Position', '8', '[\'Networking\',\'Cisco\',\'Routing and Switching\']', '6', 'Hacker00619', 'Seattle'),
    ('2024-01-23 02:45:00', 'Business Development Manager', 'Sales Executive', 'Business Development Manager Role', '9', '[\'Business Development\',\'Sales\',\'Client Acquisition\']', '7', 'Hacker00619', 'Sydney'),
    ('2024-01-26 03:30:00', 'Project Manager', 'Agile Project Lead', 'Project Manager Position', '8', '[\'Project Management\',\'Agile\',\'Scrum\']', '9', 'jash', 'Los Angeles'),
    ('2024-01-28 04:15:00', 'Technical Support Engineer', 'Help Desk Support', 'Technical Support Engineer Role', '9', '[\'Technical Support\',\'Customer Service\',\'Troubleshooting\']', '5', 'Hacker00619', 'Toronto');
