package com.cykj.survey.bean;

public class WeatherResultBean {

    private String resultcode;

    private String reason;

    private RetDataEntity result;

    private String error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RetDataEntity getResult() {
        return result;
    }

    public void setResult(RetDataEntity result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public class RetDataEntity {
        private SK sk;

        public SK getSk() {
            return sk;
        }

        public void setSk(SK sk) {
            this.sk = sk;
        }
    }

    public class SK{
        private String temp;

        private String wind_direction;

        private String wind_strength;

        private String humidity;

        private String time;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getWind_direction() {
            return wind_direction;
        }

        public void setWind_direction(String wind_direction) {
            this.wind_direction = wind_direction;
        }

        public String getWind_strength() {
            return wind_strength;
        }

        public void setWind_strength(String wind_strength) {
            this.wind_strength = wind_strength;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public class Today{
        private String temperature;

        private String weather;

    }

}
