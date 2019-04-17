var Address = function () {

    var APP = "tumor";
    var baseUrl = "/" + APP + "/area/query";
    var provinceUrl = baseUrl + "?level=1";
    var cityUrl = baseUrl + "?level=2&pcode=";
    var countyUrl = baseUrl + "?level=3&pcode=";
    var townUrl = baseUrl + "?level=4&pcode=";
    var committeeUrl = baseUrl + "?level=5&pcode=";

    setAddressUrl = function (childId, id, url) {
        var input = mini.get(id);
        var child = mini.get(childId);
        var value = input.getValue();
        if (value != undefined && value != null && value != "") {
            child.setUrl(url + value);
        }
    };

    setInputEmpty = function (id) {
        mini.get(id).setValue("");
        mini.get(id).setText("");
    };
    isEmpty = function (v) {
        return v == null || v == "" || v == undefined;
    };
    return {
        getProvinceUrl : function () {
            return provinceUrl;
        },
        getCityUrl :function (pcode) {
            return cityUrl + pcode;
        },
        getCountyUrl :function (pcode) {
            return countyUrl + pcode;
        },
        getTownUrl :function (pcode) {
            return townUrl + pcode;
        },
        getCommitteeUrl :function (pcode) {
            return committeeUrl + pcode;
        },
        setAddressCity: function (prefix) {
            setAddressUrl(prefix + 'city', prefix + 'province', cityUrl);
            setInputEmpty(prefix + 'city');
            setInputEmpty(prefix + 'county');
            mini.get(prefix + "county").setData([]);
            setInputEmpty(prefix + "town");
            mini.get(prefix + "town").setData([]);
            setInputEmpty(prefix + "committee");
            mini.get(prefix + "committee").setData([]);
        },
        setAddressCounty: function (prefix) {
            setAddressUrl(prefix + 'county', prefix + 'city', countyUrl);
            setInputEmpty(prefix + "town");
            mini.get(prefix + "town").setData([]);
            setInputEmpty(prefix + "committee");
            mini.get(prefix + "committee").setData([]);
        },
        setAddressTown: function (prefix) {
            setAddressUrl(prefix + 'town', prefix + 'county', townUrl);
            setInputEmpty(prefix + "committee");
            mini.get(prefix + "committee").setData([]);
        },
        setAddressCommittee: function (prefix) {
            setAddressUrl(prefix + 'committee', prefix + 'town', committeeUrl);
        },
        getAddress: function (prefix) {
            var mini_province = mini.get(prefix + "province").getValue();
            var mini_province_text = mini.get(prefix + "province").getText();
            var mini_city = mini.get(prefix + "city").getValue();
            var mini_city_text = mini.get(prefix + "city").getText();
            var mini_county = mini.get(prefix + "county").getValue();
            var mini_county_text = mini.get(prefix + "county").getText();
            var mini_town = mini.get(prefix + "town").getValue();
            var mini_town_text = mini.get(prefix + "town").getText();
            var mini_committee = mini.get(prefix + "committee").getValue();
            var mini_committee_text = mini.get(prefix + "committee").getText();
            var mini_detail = mini.get(prefix + "detail").getValue();
            var text = mini_province_text + mini_city_text + mini_county_text + mini_town_text + mini_committee_text + mini_detail;
            return {
                province: mini_province,
                provinceName: mini_province_text,
                city: mini_city,
                cityName: mini_city_text,
                county: mini_county,
                countyName: mini_county_text,
                town: mini_town,
                townName: mini_town_text,
                committee: mini_committee,
                committeeName: mini_committee_text,
                detail: mini_detail,
                text: text
            }
        },
        setAddress: function (prefix, province, city, county, town, committee, detail) {
            var mini_province = mini.get(prefix + "province");
            var mini_city = mini.get(prefix + "city");
            var mini_county = mini.get(prefix + "county");
            var mini_town = mini.get(prefix + "town");
            var mini_committee = mini.get(prefix + "committee");
            if (!isEmpty(province)) {
                mini_province.setValue(province);
                mini_city.setUrl(cityUrl + province);
                if (!isEmpty(city)) {
                    mini_city.setValue(city);
                    mini_county.setUrl(countyUrl + city);
                    if (!isEmpty(county)) {
                        mini_county.setValue(county);
                        mini_town.setUrl(townUrl + county);
                        if (!isEmpty(town)) {
                            mini_town.setValue(town);
                            mini_committee.setUrl(committeeUrl + town);
                            if (!isEmpty(committee)) {
                                mini_committee.setValue(committee);
                            }
                        }
                    }
                }
            }
            if (!isEmpty(detail)) {
                mini.get(prefix + "detail").setValue(detail);
            }
        }
    }
}();