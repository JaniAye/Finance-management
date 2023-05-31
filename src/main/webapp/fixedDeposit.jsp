<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Finance Management System - Fixed Deposit</title>
    <link rel="stylesheet" href="css/fixedDeposit.css">
</head>
<body>

    <section>
      <div class="main-form-container">
        <div id="form_section" class="form-container">
          <div class="FixedDeposit-form form-wraper ">
            <div>
              <div class="form-title">
                <h2>Fixed Deposits</h2>
              </div>
              <form action="FixedDeposit" method="get" id="myForm">
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
                    <input class="myInput" type="text"  name="accNumber" value="${result.fdAcc}" readonly/>
                  </span>
                </div>
              </div>
              <div class="input-group">
                  <label class="lbl" >Maturity date : </label>
                <div class="box">
                  <span>
                    <input class="myInput" type="text" name="mDate" value="${result.mDate}" readonly/>
                  </span>
                </div>
              </div>
              <div class="input-group">
                  <label class="lbl" >Interest Rate : </label>
                <div class="box">
                  <span>
                    <input class="myInput" type="text" name="interestRate" value="20%" readonly/>
                  </span>
                </div>
              </div>
              <div class="input-group">
                  <label class="lbl" >Interest Earned : </label>
                <div class="box">
                  <span>
                    <input class="myInput" type="text" name="interest" value="${result.interestEarned}" readonly/>
                  </span>
                </div>
              </div>

              <div class="action-button">
                <button>Search</button>
              </div>

<%--              </form>--%>

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
          console.log("loggggg")
          console.log(apiCallMade)
          if (!apiCallMade) {
              console.log("inner loggggg")
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
