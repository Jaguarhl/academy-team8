package academy.team8.com.footballfanlocator.model;

/**
 * Created by dmitry on 10.06.2018.
 */

public class CountryItem {
    int countryId;
    String countryPk;
    String flagPic;
    String countryName;

    public CountryItem(int id, String countryPk, String countryName, String flagPic) {
        this.countryId = id;
        this.countryPk = countryPk;
        this.countryName = countryName;
        this.flagPic = flagPic;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getFlagPic() {
        return flagPic;
    }

    public void setFlagPic(String flagPic) {
        this.flagPic = flagPic;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryPk() {
        return this.countryPk;
    }
}


