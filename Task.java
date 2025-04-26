import java.util.Date;

public class Task {
    private String description;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public Task() {

    }

    public Task(String description) {
        this.description = description;
        this.status = "To Do";
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return this.getDescription() + " || " + this.getStatus() + " || "
                + this.getCreatedAt() + " || " + this.getUpdatedAt();
    }
}
