1
CREATE TABLE Naplo5 (
    Esemeny VARCHAR2(20),
    Adat VARCHAR2(100),
    Datum TIMESTAMP(6)
);

-------------------------
2
CREATE TABLE Vasarlo (
    VID CHAR(3),
    NEV VARCHAR2(30) NOT NULL,
    CIM VARCHAR2(30),
    FIZMOD NUMBER(3,0)
);

INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v01', 'Kék Alma', 'Mc. Kék u.12', 2);
INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v02', 'Zöld Gabi', 'Mc. Hó u.72', 3);
INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v03', 'Feke F...', 'Mc.Kőu.25', 1);
INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v04', 'Korcs Éva', 'Eger. Lap...', 1);
INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v05', 'Kis Béla', 'Eger. Bé u.9', 2);
INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v06', 'Kis Jenő', 'Eger. Cé ...', 3);
INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v07', 'Kis Noé', 'Eger. Cé ...', 4);
INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v08', 'Kis Tas', 'Eger. Cé ...', 1);
INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v09', 'Hó Manó', 'Nyék. Tóu.74', 2);
INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v10', 'Ká Rozi', 'Nyék. Káu.5', 3);
INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v11', 'Víz Jenő', 'Mc. Útu.39', 3);

COMMIT;

----------------------
3
CREATE OR REPLACE TRIGGER TB AFTER INSERT ON Vasarlo FOR EACH ROW
BEGIN
    INSERT INTO Naplo5 (Esemeny, Adat, Datum)
    VALUES ('Beszúrás', :NEW.VID || ' ' || :NEW.NEV || ' ' || :NEW.CIM, CURRENT_TIMESTAMP);
END;

----------------------
4
INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v12', 'Teszt Elek', 'Miskolc, Fő út 1', 1);
INSERT INTO Vasarlo (VID, NEV, CIM, FIZMOD) VALUES ('v13', 'Próba Petra', 'Budapest, Kis u. 2', 2);

----------------------
4a
SELECT * FROM Vasarlo;

----------------------
4b
SELECT * FROM Naplo5;

----------------------
4c
DROP TRIGGER TB;

---------------------
5
CREATE OR REPLACE TRIGGER TM
AFTER UPDATE ON Vasarlo
FOR EACH ROW
BEGIN
    INSERT INTO Naplo5 (Esemeny, Adat, Datum)
    VALUES (
        'Módosítás', 
        :OLD.NEV || '_' || :NEW.NEV || ', ' || :OLD.CIM || '_' || :NEW.CIM, 
        CURRENT_TIMESTAMP
    );
END;

----------------------
6
UPDATE Vasarlo SET NEV = 'Teszt Aladár', CIM = 'Miskolc, Erdősor 5' WHERE VID = 'v12';
UPDATE Vasarlo SET NEV = 'Próba Panna', CIM = 'Budapest, Nagy u. 10' WHERE VID = 'v13';

----------------------
6a
DROP TRIGGER TM;

---------------------
7
CREATE OR REPLACE TRIGGER TT
AFTER DELETE ON Vasarlo
FOR EACH ROW
BEGIN
    INSERT INTO Naplo5 (Esemeny, Adat, Datum)
    VALUES ('Törlés', :OLD.VID || ' ' || :OLD.NEV, CURRENT_TIMESTAMP);
END;
/

----------------------
7a
DELETE FROM Vasarlo WHERE VID IN ('V20', 'v12', 'v13');

---------------------
7b
SELECT * FROM Naplo5;

---------------------
7c
DROP TRIGGER TT;












































