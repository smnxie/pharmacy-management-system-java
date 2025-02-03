import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineService implements IMedicineService {

    @Override
    public void addMedicine(Medicine medicine) {
        String sql = "INSERT INTO medicines (name, manufacturer, batch_number, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medicine.getName());
            stmt.setString(2, medicine.getManufacturer());
            stmt.setString(3, medicine.getBatchNumber());
            stmt.setDouble(4, medicine.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicines";
        try (Connection conn = DatabaseHandler.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                medicines.add(new Medicine(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("manufacturer"),
                        rs.getString("batch_number"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicines;
    }

    @Override
    public boolean purchaseMedicine(String batchNumber) {
        String sql = "DELETE FROM medicines WHERE batch_number = ?";
        try (Connection conn = DatabaseHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, batchNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
