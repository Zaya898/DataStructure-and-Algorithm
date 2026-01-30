package BiyDaalt;

public class Major {
    public String code;
    public String name;

    public Major(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public boolean matchesStudent(String studentCode) {
        if (studentCode.length() >= 7) {
            String majorCode = studentCode.substring(3, 7);
            return majorCode.equals(this.code);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.code + " - " + this.name;
    }
}
