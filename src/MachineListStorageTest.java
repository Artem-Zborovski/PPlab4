import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

public class MachineListStorageTest {

    private MachineListStorage machineStorage;

    @BeforeEach
    public void setUp() {
        machineStorage = new MachineListStorage();
    }

    @Test
    public void testAddMachine() {
        WashingMachine machine = new DomesticWashingMachine(1, "Model1", 1500, 1200, Date.valueOf("2020-01-01"), 500.0);
        machineStorage.addMachine(machine);

        List<WashingMachine> machines = machineStorage.getMachines();

        // Проверяем, что машина была добавлена
        assertEquals(1, machines.size(), "Количество машин должно быть 1");
        assertEquals(machine, machines.get(0), "Добавленная машина должна совпадать с первой машиной в списке");
    }

    @Test
    public void testGetMachines() {
        WashingMachine machine1 = new DomesticWashingMachine(1, "Model1", 1500, 1200, Date.valueOf("2020-01-01"), 500.0);
        WashingMachine machine2 = new IndustrialWashingMachine(2, "Model2", 3000, 1400, Date.valueOf("2019-05-15"), 2000.0);

        machineStorage.addMachine(machine1);
        machineStorage.addMachine(machine2);

        List<WashingMachine> machines = machineStorage.getMachines();

        // Проверяем, что машины были добавлены
        assertEquals(2, machines.size(), "Количество машин должно быть 2");
        assertEquals(machine1, machines.get(0), "Первая машина должна совпадать с machine1");
        assertEquals(machine2, machines.get(1), "Вторая машина должна совпадать с machine2");
    }

    @Test
    public void testEmptyStorage() {
        List<WashingMachine> machines = machineStorage.getMachines();
        
        // Проверяем, что хранилище изначально пусто
        assertTrue(machines.isEmpty(), "Хранилище должно быть пустым");
    }
}
