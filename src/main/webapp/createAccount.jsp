<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 5/19/2023
  Time: 10:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Finance Management System - Create Account</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="css/createAccountForm.css">

</head>
<body>
<section>
    <div class="main-form-container">
        <div id="form_section" class="form-container">
            <div class="CreateAcc-form form-wraper ">
                <div>
                   <div class="form-title">
                        <h2>Create Account</h2>
                    </div>
                        <div class="input-group">
                            <div class="box">
                              <span>
                                  <select class="myInput" id="accountType" name="accountType" required >
                                        <option value="loan">Loan</option>
                                        <option value="fixedDeposit">Fixed Deposit</option>
                                        <option value="savings">Savings</option>
                                    </select><br>
            <%--                    <input placeholder="User Name" class="myInput" type="text" />--%>
                              </span>
                            </div>
                        </div>
                        <div class="input-group">
                            <div class="box">
                              <span>
                                <input placeholder="Amount" id="amount" class="myInput" type="text"  name="damount"/>
                              </span>
                            </div>
                        </div>
                        <div class="action-button">
                            <button id="crtAccount" onclick="sendRequest()">Create account</button>
                        </div>

                    <div class="action-button">
                        <button onclick="redirectToHome()">Home</button>
                    </div>

                </div>
            </div>

        </div>

    </div>
</section>
<script>

    function sendRequest() {
        var dropdown = document.getElementById('accountType');
        var selectedValue = dropdown.options[dropdown.selectedIndex].value;
        var amount = document.getElementById("amount").value;

        if (amount==='' || amount<1000){
            showAlert("Enter Valid deposit amount...");
        }else {
            $.ajax({
                url: "Accounts",
                type: "POST",
                data: {
                    accountType: selectedValue,
                    damount : amount
                },
                success: function(response) {
                    if (response.status === "fail"){
                        showAlert("Enter deposit amount...");
                        document.getElementById("amount").focus();
                    }
                    if (response.status === "notValid"){
                        showAlert("Invalid deposit amount...");

                        window.location.href = "accounts.jsp";
                    }
                    if (response.status === "success"){
                        showAlert("Account created...");

                        window.location.href = "accounts.jsp";
                    }
                },
                error: function() {
                    showAlert("An error occurred while processing the request.");
                }
            });
        }
        // document.getElementById('selectedValue').value = selectedValue;
    }
    function showAlert(message) {
        alert(message);
    }

    window.onload = function() {
      var selected = sessionStorage.getItem("pressedTab");
        var selectElement = document.getElementById('accountType');

        if (selected!=null) {
            selectElement.value = selected;
            selectElement.disabled = true;
        }

        sessionStorage.removeItem("pressedTab");
    }

    function redirectToHome() {
        window.location.href = "home.jsp";
    }
</script>
</body>
</html>
