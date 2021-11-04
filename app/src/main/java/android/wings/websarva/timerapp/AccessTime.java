package android.wings.websarva.timerapp;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class AccessTime {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "access_time")
    private String accessTime;

    public AccessTime(String accessTime) {
        this.accessTime = accessTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(int id) {
        return id;
    }

    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }

    public String getAccessTime() {
        return accessTime;
    }
}
