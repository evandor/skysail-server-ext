CREATE TABLE FOLDER(
    PID INTEGER NOT NULL ,
    FOLDERNAME VARCHAR(255) ,
    owner INTEGER NOT NULL ,
    PARENT_PID INTEGER ,
    PRIMARY KEY(PID)
);

CREATE TABLE NOTE(
    PID INTEGER NOT NULL ,
    CONTENT BLOB ,
    CREATED TIMESTAMP ,
    TITLE VARCHAR(255) ,
    owner INTEGER NOT NULL ,
    PARENT_PID INTEGER ,
    PRIMARY KEY(PID)
);

ALTER TABLE FOLDER 
ADD CONSTRAINT FK_FOLDER_PARENT_PID FOREIGN KEY(PARENT_PID) REFERENCES FOLDER(PID);

ALTER TABLE FOLDER 
ADD CONSTRAINT FK_FOLDER_owner FOREIGN KEY(owner) REFERENCES um_users(ID);

ALTER TABLE NOTE 
ADD CONSTRAINT FK_NOTE_PARENT_PID FOREIGN KEY(PARENT_PID) REFERENCES FOLDER(PID);

ALTER TABLE NOTE 
ADD CONSTRAINT FK_NOTE_owner FOREIGN KEY(owner) REFERENCES um_users(ID);

/*
cannot use IF NOT EXISTS in derby
CREATE TABLE SEQUENCE(
    SEQ_NAME VARCHAR(50) NOT NULL ,
    SEQ_COUNT DECIMAL(31) ,
    PRIMARY KEY(SEQ_NAME)
);
*/

INSERT INTO SEQUENCE(
    SEQ_NAME ,
    SEQ_COUNT
) values(
    'EXT_NOTES_SEQ',
    0
);

INSERT INTO SEQUENCE(
    SEQ_NAME ,
    SEQ_COUNT
) values(
    'EXT_NOTES_NOTE_SEQ',
    0
);