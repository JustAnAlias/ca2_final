

var addCompany = function () {
//    var formData = JSON.stringify($("#userForm").serializeArray());
    var company = {
        "companyName" : document.getElementById('companyName').value,
        "cvr" : document.getElementById('cvr').value,
        "description" : document.getElementById('description').value,
        "numEmployees" : document.getElementById('numEmployees').value,
        "marketValue" : document.getElementById('marketValue').value
        };
    

    $.ajax({
        type: "POST",
        url: "api/search/",
        data: JSON.stringify(company),
        success: function () {},
        dataType: "json",
        contentType: "application/json"
    });
};