CREATE TABLE Termek (
    TKOD CHAR(3) NOT NULL,
    NEV VARCHAR2(20) NOT NULL,
    AR NUMBER(38,0),
    LEIRAS VARCHAR2(20),
    KATEGORIA CHAR(3) NOT NULL
);

------------------

INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t01', 'sör', 200, 'világos', 'k02');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t02', 'bor', 200, 'vörös', 'k02');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t03', 'zsömle', 20, 'kerek', 'k01');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t04', 'zsír', 100, 'disznó', 'k01');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t05', 'paprika', 100, 'zöld', 'k01');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t06', 'csipsz', 300, 'sajtos', 'k01');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t07', 'csipsz', 400, 'retkes', 'k01');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t11', 'Fű', 5000, 'KO', 'k04');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t13', 'Benzin', 250, 'Olcsó', 'k04');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t14', 'Nő', 10000, 'Szőke', 'k04');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t15', 'Nő', 20000, 'Barna', 'k04');

COMMIT;

-------------------

CREATE OR REPLACE TRIGGER TBT
AFTER INSERT ON Termek
FOR EACH ROW
BEGIN
    INSERT INTO Naplo5 (Esemeny, Adat, Datum)
    VALUES ('Termék beszúrás', :NEW.TKOD || ' ' || :NEW.NEV || ' ' || :NEW.AR, CURRENT_TIMESTAMP);
END;
/

--------------------

INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t20', 'Kóla', 350, '0.5L', 'k02');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t21', 'Fanta', 320, '0.5L', 'k02');

-- 4a----------------
SELECT * FROM Termek;

-- 4b------------------
SELECT * FROM Naplo5;

-- 4c-----------------
DROP TRIGGER TBT;

---------------------











































































