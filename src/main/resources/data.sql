DROP TABLE IF EXISTS EVENT;

CREATE TABLE EVENT(
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      event_name VARCHAR(60) NOT NULL,
                      description VARCHAR (500) NOT NULL,
                      owner_id INT NOT NULL ,
                      participants_id INT NOT NULL
);

INSERT INTO EVENT (event_name, description, owner_id, participants_id) VALUES
                                                                           ('Christmas party', 'business Christmas party of Raiffeisen Bank', '1', '125'),
                                                                           ('St.Nicolas party', 'welcome St.Nicolas in kindergarden', '2', '15');
