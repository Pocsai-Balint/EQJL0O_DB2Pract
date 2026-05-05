-- Könyv tábla létrehozása
CREATE TABLE Konyv (
    KonyvID NUMBER PRIMARY KEY,
    Cim VARCHAR2(100),
    Szerzo VARCHAR2(100),
    Mufaj VARCHAR2(50),
    KiadasEve NUMBER,
    Ar NUMBER
);


-- Napló tábla a módosításokhoz
CREATE TABLE Konyv_Log (
    LogID NUMBER GENERATED ALWAYS AS IDENTITY,
    Esemeny_Tipusa VARCHAR2(50),
    Erintett_KonyvID NUMBER,
    Esemeny_Ideje TIMESTAMP DEFAULT SYSTIMESTAMP
);


CREATE SEQUENCE seq_konyv_id START WITH 1 INCREMENT BY 1;
-- Trigger: Kulcs érték megadása
CREATE OR REPLACE TRIGGER trg_konyv_autokey
BEFORE INSERT ON Konyv
FOR EACH ROW
BEGIN
    IF :NEW.KonyvID IS NULL THEN
        :NEW.KonyvID := seq_konyv_id.NEXTVAL;
    END IF;
END;
/

-- Trigger: az ár nem lehet negatív
CREATE OR REPLACE TRIGGER trg_konyv_control
BEFORE UPDATE OR INSERT ON Konyv
FOR EACH ROW
BEGIN
    IF :NEW.Ar < 0 THEN
        
        RAISE_APPLICATION_ERROR(-20001, 'Hiba: A könyv ára nem lehet negatív!');
    END IF;
END;
/

-- Trigger: Módosítások naplózása
CREATE OR REPLACE TRIGGER trg_konyv_log
AFTER INSERT OR UPDATE OR DELETE ON Konyv
FOR EACH ROW
DECLARE
    v_esemeny VARCHAR2(50);
    v_id NUMBER;
BEGIN
    IF INSERTING THEN
        v_esemeny := 'ÚJ KÖNYV FELVÉTEL';
        v_id := :NEW.KonyvID;
    ELSIF UPDATING THEN
        v_esemeny := 'KÖNYV ADAT MÓDOSÍTÁS';
        v_id := :NEW.KonyvID;
    ELSIF DELETING THEN
        v_esemeny := 'KÖNYV TÖRLÉS';
        v_id := :OLD.KonyvID;
    END IF;
    
    INSERT INTO Konyv_Log (Esemeny_Tipusa, Erintett_KonyvID) 
    VALUES (v_esemeny, v_id);
END;
/

CREATE OR REPLACE PACKAGE konyv_pkg IS
    PROCEDURE beszuras(p_cim VARCHAR2, p_szerzo VARCHAR2, p_mufaj VARCHAR2, p_ev NUMBER, p_ar NUMBER);
    PROCEDURE modositas(p_id NUMBER, p_uj_ar NUMBER);
    PROCEDURE torles(p_id NUMBER);
    FUNCTION lekerdezes_mezok(p_id NUMBER) RETURN VARCHAR2;
    FUNCTION aggregalt_atlagar(p_mufaj VARCHAR2) RETURN NUMBER;
END konyv_pkg;
/


CREATE OR REPLACE PACKAGE BODY konyv_pkg IS

   
    PROCEDURE beszuras(p_cim VARCHAR2, p_szerzo VARCHAR2, p_mufaj VARCHAR2, p_ev NUMBER, p_ar NUMBER) IS
    BEGIN
        INSERT INTO Konyv (Cim, Szerzo, Mufaj, KiadasEve, Ar)
        VALUES (p_cim, p_szerzo, p_mufaj, p_ev, p_ar);
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Hiba történt a beszúrás során: ' || SQLERRM);
            ROLLBACK;
    END beszuras;

    -- eljárás adatok módosítására 
    PROCEDURE modositas(p_id NUMBER, p_uj_ar NUMBER) IS
    BEGIN
        UPDATE Konyv SET Ar = p_uj_ar WHERE KonyvID = p_id;
        
        
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Nincs ilyen ID-jú könyv, így nem történt módosítás.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('A módosítás sikeres.');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
    END modositas;

    -- adatok törlésére
    PROCEDURE torles(p_id NUMBER) IS
    BEGIN
        DELETE FROM Konyv WHERE KonyvID = p_id;
        COMMIT;
    END torles;

    -- rekord mezőinek lekérdezésére
    FUNCTION lekerdezes_mezok(p_id NUMBER) RETURN VARCHAR2 IS
        v_eredmeny VARCHAR2(200);
    BEGIN
        SELECT Cim || ' (Szerző: ' || Szerzo || ')' INTO v_eredmeny
        FROM Konyv
        WHERE KonyvID = p_id;
        
        RETURN v_eredmeny;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN  
            RETURN 'Nincs találat erre az ID-ra.';
    END lekerdezes_mezok;

   
    FUNCTION aggregalt_atlagar(p_mufaj VARCHAR2) RETURN NUMBER IS
        
        CURSOR c_konyvek IS
            SELECT Ar FROM Konyv WHERE Mufaj = p_mufaj;
            
        v_osszeg NUMBER := 0;
        v_darabszam NUMBER := 0;
        v_aktualis_ar NUMBER;
    BEGIN
        OPEN c_konyvek;
        LOOP
            FETCH c_konyvek INTO v_aktualis_ar;
            EXIT WHEN c_konyvek%NOTFOUND;
            
            v_osszeg := v_osszeg + v_aktualis_ar;
            v_darabszam := v_darabszam + 1;
        END LOOP;
        CLOSE c_konyvek;

        IF v_darabszam > 0 THEN
            RETURN v_osszeg / v_darabszam;
        ELSE
            RETURN 0;
        END IF;
    END aggregalt_atlagar;

END konyv_pkg;
/