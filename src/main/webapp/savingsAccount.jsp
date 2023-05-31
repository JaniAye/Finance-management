<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 5/19/2023
  Time: 9:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/savingsAccountsForm.css">
</head>
<body>
    <section>
        <div class="main-form-container">
            <div id="form_section" class="form-container">
                <div class="savingsAccount-form form-wraper ">
                    <div>
                        <div class="form-title">
                            <h2>Savings Account</h2>
                        </div>
                        <div class="input-group">
<%--                            <div class="box">--%>
                      <span>
<%--                      <span class="abc">--%>

                          <h4>Account balance : ></h4>

<%--                        <input placeholder="User Name" class="myInput" type="text" />--%>
                      </span>
<%--                            </div>--%>
                        </div>

                        <div class="action-button">
                            <button>Deposit</button>
                        </div>
                        <div class="action-button">
                            <button>Withdraw</button>
                        </div>
                        <div class="action-button">
                            <button onclick="redirectToTransPg()">Transfer</button>
                        </div>

                    </div>
                </div>

            </div>

        </div>
    </section>

    <script>
        function redirectToTransPg() {
            window.location.href = "transaction.jsp";
        }
    </script>
</body>
</html>
