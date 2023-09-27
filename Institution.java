/*
 * Name: Habeeb Sowemimo
 * Class: EECS 2500
 * Project: zero
 */
public class Institution {
    protected String officialName;
    protected String nickname;
    protected String city;
    protected String state;
    protected int studentBody;
    protected int yearFounded;
    public String getCity() {
        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getState() {

        return state;
    }

    public void setState(String state) {

        this.state = state;
    }

    public int getStudentBody() {

        return studentBody;
    }

    public void setStudentBody(int studentBody) {

        this.studentBody = studentBody;
    }

    public int getYearFounded() {

        return yearFounded;
    }

    public void setYearFounded(int yearFounded) {

        this.yearFounded = yearFounded;
    }

    public String getNickname() {

        return nickname;
    }

    public void setNickname(String nickname) {

        this.nickname = nickname;
    }

    public String getOfficialName() {

        return officialName;
    }

    public void setOfficialName(String officialName) {

        this.officialName = officialName;
    }
    public String toString(){
        return officialName + "\n" + nickname + "\n" + city + "\n" + state + "\n" +studentBody + "\n" + yearFounded;
    }

    public Institution(String officialName, String nickname, String city, String state, int yearFounded, int studentBody){
        this.officialName = officialName;
        this.nickname = nickname;
        this.city = city;
        this.state = state;
        this.yearFounded = yearFounded;
        this.studentBody = studentBody;
    }
}