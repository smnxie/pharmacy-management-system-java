import java.util.List;

public interface IMedicineService {
    void addMedicine(Medicine medicine);
    List<Medicine> getAllMedicines();
    boolean purchaseMedicine(String batchNumber);
}
