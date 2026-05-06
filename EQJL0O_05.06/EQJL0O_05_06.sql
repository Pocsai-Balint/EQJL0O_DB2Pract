CREATE TABLE Termek (
    TKOD CHAR(3 BYTE) NOT NULL,
    NEV VARCHAR2(20 BYTE) NOT NULL,
    AR NUMBER(38,0),
    LEIRAS VARCHAR2(20 BYTE),
    KATEGORIA CHAR(3 BYTE) NOT NULL
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
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t13', 'Benzin', 250, 'Óccsó', 'k04');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t14', 'Nő', 10000, 'Szőke', 'k04');
INSERT INTO Termek (TKOD, NEV, AR, LEIRAS, KATEGORIA) VALUES ('t15', 'Nő', 20000, 'Barna', 'k04');

-------------------

-- 4. TArFigy.sql
CREATE OR REPLACE TRIGGER TArFigy
BEFORE UPDATE OF AR ON Termek
FOR EACH ROW
BEGIN
    IF :NEW.AR >= :OLD.AR * 1.2 OR :NEW.AR <= :OLD.AR * 0.8 THEN
        DBMS_OUTPUT.PUT_LINE('Nem megengedett mértékű a módosítás');
        -- Ha a módosítást meg is kell akadályozni, a fenti sor helyett/mellett:
        -- RAISE_APPLICATION_ERROR(-20001, 'Nem megengedett mértékű a módosítás');
    END IF;
END;
/

-- 5. -----------
EXEC ModT('t05', 130);
-- Vagy eljárás nélkül: UPDATE Termek SET AR = 130 WHERE TKOD = 't05';

-- 6. ------------
EXEC ModT('t05', 80);
-- Vagy eljárás nélkül: UPDATE Termek SET AR = 80 WHERE TKOD = 't05';

-- 7. -------------
EXEC ModT('t05', 70);
-- Vagy eljárás nélkül: UPDATE Termek SET AR = 70 WHERE TKOD = 't05';