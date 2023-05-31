
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Finance Management System - Transaction</title>
<%--    <link rel="stylesheet" href="css/transactionForm.css">--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="css/transaction.css">
</head>
<body>


<section>
    <div class="main-form-container">
        <div id="form_section" class="form-container">
            <div class="Transaction-form form-wraper ">
                <div>
                   <div class="form-title">
                        <h2>Make Transactions</h2>
                    </div>

<%--                    <form id="transactionForm" action="Transaction" method="post">--%>
                        <div class="input-group">
                            <label class="lbl" >Transaction Type : </label>
                            <div class="box">
                              <span>
                                  <select class="myInput" id="accType" name="type" onchange="changeButtonText()" required >
                                        <option value="dipo">Deposit</option>
                                        <option value="withdraw">Withdraw</option>
                                    </select><br>
                              </span>
                            </div>
                        </div>
                    <form id="drpLst" action="Transaction" method="get">
                            <div class="input-group">
                                <label class="lbl" >Account Type : </label>
                                <div class="box">
                                  <span>
                                      <select class="myInput" id="accountType" name="transactionType" onchange="typeChange()"  required >
                                            <option value="sav">Savings</option>
                                            <option value="fd">Fixed Deposit</option>
                                        </select><br>
                                  </span>
                                </div>
                            </div>
                        <div class="input-group">
                            <label class="lbl" >Account Number : </label>
                            <div class="box">
                                  <span>
                                      <select class="myInput" id="accountId" name="accountId"  required >
                                      </select><br>
                                  </span>
                            </div>
                        </div>
                    </form>
                        <div class="input-group">
                            <label class="lbl" >Amount : </label>
                            <div class="box">
                              <span>
                                <input id="amount" placeholder="Type Here..." class="myInput" type="text"  name="damount" onkeypress="return isNumberKey(event)"/>
                              </span>
                            </div>
                        </div>
                        <div class="input-group">
                            <label class="lbl" >Remark : </label>
                            <div class="box">
                              <span>
                                <input id="remark" placeholder="Type Here..." class="myInput" type="text"  name="remark"/>
                              </span>
                            </div>
                        </div>

<%--                    </form>--%>
                    <div class="action-button" >
<%--                    <div class="action-button" onclick="validateForm()">--%>
                        <button id="trnsbtn" >Deposit</button>
                    </div>


                    <div class="action-button">
                        <button onclick="redirectToHomepg()">Home</button>
                    </div>

                </div>
            </div>

        </div>

    </div>
</section>
<script>
    var apiCallMade = sessionStorage.getItem("apiCallMade");
    var selectType = sessionStorage.getItem("selectType");
    window.onload = function() {
        setDropdown();
    }
    function setDropdown(){
        var selectElement = document.getElementById('accountType');


        if (!apiCallMade) {
            document.getElementById("drpLst").submit();
            sessionStorage.setItem("apiCallMade", true);
        }

        var accountIds = JSON.parse('${accountIdsJson}');
        var dropdown = document.getElementById('accountId');
        if (selectType!=null)
            selectElement.value=selectType;
        accountIds.forEach(function(accountId) {
            var option = document.createElement('option');
            option.value = accountId;
            option.text = accountId;
            dropdown.appendChild(option);
        });
    }

    function typeChange(){
        var type= document.getElementById("accountType").value;
        sessionStorage.setItem("selectType", type);
        sessionStorage.removeItem("apiCallMade");
        apiCallMade = sessionStorage.getItem("apiCallMade");
        setDropdown();

    }
    function submitForm() {
        var selectedAccountId = $('#accountId').val();

        $.ajax({
            type: 'POST',
            url: 'your-servlet-url',
            data: {accountId: selectedAccountId},
            success: function(response) {
                // Handle the API response
                $('#apiResponse').text(response);
            },
            error: function() {
                alert('An error occurred while processing the request.');
            }
        });
    }

    function redirectToHomepg() {
        sessionStorage.removeItem("apiCallMade");
        sessionStorage.removeItem("selectType");

        window.location.href = "accounts.jsp";
    }
    function isNumberKey(event) {
        var charCode = (event.which) ? event.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
    function changeButtonText() {
        var dropdown = document.getElementById("accType");
        var button = document.getElementById("trnsbtn");

        var selectedOption = dropdown.value;
        var buttonText = "";

        switch (selectedOption) {
            case "dipo":
                buttonText = "Deposit";
                break;
            case "withdraw":
                buttonText = "Withdraw";
                break;
            default:
                buttonText = "Deposit";
                break;
        }

        button.innerText = buttonText;
    }

    $(document).ready(function() {

        $("#trnsbtn").click(function() {

            var accNO = document.getElementById("accountId").value;
            var type = document.getElementById("accType").value;
            var transactionType = document.getElementById("accountType").value;
            var amount = document.getElementById("amount").value;
            var reMark = document.getElementById("remark").value;


            if (accNO === '' || amount === '' || reMark==='') {
                alert("Please fill in all the fields");
                document.getElementById("accNO").focus();
            }else {
                $.ajax({
                    url: "Transaction",
                    type: "POST",
                    data: {
                        accNo: accNO,
                        type: type,
                        transactionType: transactionType,
                        remark: reMark,
                        damount: amount

                    },
                    success: function(response) {

                        if (response.result === "fail"){
                            document.getElementById("amount").focus();
                            showAlert("Incorrect account number.Check again...");
                        }
                        if (response.result === "success"){
                            showAlert("Transaction successful...");
                            document.getElementById("amount").value = "";
                            document.getElementById("remark").value = "";

                            document.getElementById("amount").focus();
                        }
                    },
                    error: function() {
                        showAlert("An error occurred while processing the request.");
                    }
                });
            }

        })});
    function validateForm() {
        var accNo = document.getElementById("accNO").value;
        var amount = document.getElementById("amount").value;
        var reMark = document.getElementById("remark").value;

        if (accNo === '' || amount === '' || reMark==='') {
            alert("Please fill in all the fields");
            document.getElementById("accNO").focus();
        }else {
            document.getElementById("transactionForm").submit();
        }
    }

    var dropdown = document.getElementById('accType');
    var button = document.getElementById('trnsbtn');
    var dropdown2 = document.getElementById('accountType');

    var optionsMap = {
        dipo: {
            options: ['Savings', 'Fixed Deposit'],
            count: 2
        },
        withdraw: {
            options: ['Savings'],
            count: 1
        },

    };
    dropdown.addEventListener('change', function() {
        var selectedValue = dropdown.value;
        console.log(selectedValue)
        var dropdown2Data = optionsMap[selectedValue];
        console.log(dropdown2Data)

        dropdown2.innerHTML = '';

        for (var i = 0; i < dropdown2Data.options.length; i++) {
            var option = document.createElement('option');
            option.value = dropdown2Data.options[i];
            option.text = dropdown2Data.options[i];
            dropdown2.appendChild(option);
        }

        var countElement = document.getElementById('count');
        countElement.innerText = dropdown2Data.count.toString();


        button.innerText = dropdown.options[dropdown.selectedIndex].text;
    });


    function handleResult(result) {
        if (result === "fail") {
            showAlert("Invalid Account Number...!");
        }
    }

    function showAlert(message) {
        alert(message);
    }
    // var button2 = document.getElementById("trnsbtn");

    <%--button2.addEventListener("click", function() {--%>
    <%--    console.log("aaaaaaaaaaaaaaaaaaaaa")--%>
    <%--    handleResult("${result}");--%>
    <%--});--%>
</script>
</body>
</html>
