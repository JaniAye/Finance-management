<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Finance Management System - Savings</title>
<%--  <link rel="stylesheet" href="css/savingsForm.css.css">--%>
    <link rel="stylesheet" href="css/savingsForm.css">
</head>
<body>

    <section>
      <div class="main-form-container">
        <div id="form_section" class="form-container">
          <div class="Savings-form form-wraper ">
            <div>
              <div class="form-title">
                <h2>Savings Account</h2>
              </div>
              <form action="Savings" method="get" id="myForm">
              </form>
                <%
                    Object result = request.getAttribute("result");
                    // Handle the result accordingly
                %>
<%--              <form action="User" method="post">--%>
              <div class="input-group">
                  <label class="lbl" >Account number : </label>
                <div class="box">
                  <span>
                    <input class="myInput" type="text"  name="accNumber" value="${result.account_id}" readonly/>
                  </span>
                </div>
              </div>
              <div class="input-group">
                  <label class="lbl" >Interest rate : </label>
                <div class="box">
                  <span>
                    <input class="myInput" type="text" name="rate"  value="${result.interest_rate}" readonly/>
                  </span>
                </div>
              </div>
              <div class="input-group">
                  <label class="lbl" >Balance : </label>
                <div class="box">
                  <span>
                    <input class="myInput" type="text" name="balance" value="${result.balance}" readonly/>
                  </span>
                </div>
              </div>

              <div class="action-button">
                <button onclick="redirectToPage()">Back to accounts</button>
              </div>


            </div>
          </div>

        </div>

      </div>
    </section>

    <script>

      var apiCallMade = sessionStorage.getItem("apiCallMade");

      window.onload = function() {
          if (sessionStorage.getItem("pressedTab")!=null)
              sessionStorage.removeItem("pressedTab");
          console.log(apiCallMade)
          if (!apiCallMade) {
              document.getElementById("myForm").submit();
              sessionStorage.setItem("apiCallMade", true);

          }
      }
      function redirectToPage() {
          sessionStorage.removeItem("apiCallMade");
          window.location.href = "accounts.jsp";
      }
    </script>
</body>
</html>
