<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Finance Management System - Loans</title>
  <link rel="stylesheet" href="css/loanRepayForm.css">
</head>
<body>

    <section>
      <div class="main-form-container">
        <div id="form_section" class="form-container">
          <div class="Loan-from form-wraper ">
            <div>
              <div class="form-title">
                <h2>Loan Repayment</h2>
              </div>
              <form action="Loans" method="get" id="myForm">
              </form>
                <%
                    Object result = request.getAttribute("result");
                    // Handle the result accordingly
                %>
              <form action="Loans" method="post">

              <div class="input-group">
                <label class="lbl">Account number : </label>
                <div class="box">
                  <span>
                    <input placeholder="Account number" class="myInput" type="text"  name="accNumber" value="${result.accId}" readonly/>
                  </span>
                </div>
              </div>
              <div class="input-group">
                <label class="lbl">Loan amount : </label>
                <div class="box">
                  <span>
                    <input class="myInput" type="text" name="lamount" value="${result.loanAmo}" readonly/>
                  </span>
                </div>
              </div>
                <div class="input-group">
                  <label class="lbl">Monthly Payment : </label>
                <div class="box">
                  <span>
                    <input class="myInput" type="text" name="mnPay" value="${result.monthlyInterest}" readonly/>
                  </span>
                </div>
              </div>
                <div class="input-group">
                  <label class="lbl">Total amount : </label>
                <div class="box">
                  <span>
                    <input  class="myInput" type="text" name="totAmo" value="${result.totAmount}" readonly/>
                  </span>
                </div>
              </div>

                    <div class="input-group">
                      <label class="lbl" >Re-Pay amount : </label>
                    <div class="box">
                      <span>
                        <input placeholder="Typer here..." id="chkplace" class="myInput" type="text" name="amount" onkeypress="return isNumberKey(event)"/>
                      </span>
                    </div>
                  </div>

              <div class="action-button">
                <button onclick="reloadData()">Pay</button>
              </div>
              </form>
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
      window.onload = function (){
        if (sessionStorage.getItem("pressedTab")!=null)
             sessionStorage.removeItem("pressedTab");
      }
      function redirectToPage() {
          sessionStorage.removeItem("apiCallMade");
          window.location.href = "accounts.jsp";
      }
      function reloadData() {
          sessionStorage.removeItem("apiCallMade");
          // window.location.href = "accounts.jsp";
      }
      function isNumberKey(event) {
        var charCode = (event.which) ? event.which : event.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
          return false;
        }
        return true;
      }


    </script>
</body>
</html>
