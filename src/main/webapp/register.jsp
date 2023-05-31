<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Finance Management System - Registration</title>
  <link rel="stylesheet" href="css/registerForm.css">
</head>
<body>

    <section>
      <div class="main-form-container">
        <div id="form_section" class="form-container">
          <div class="Register-form form-wraper ">
            <div>
              <div class="form-title">
                <h2>Register</h2>
              </div>

              <form id="myForm" action="User" method="post">
              <div class="input-group">
                <div class="box">
                  <span>
                    <input placeholder="User Name" class="myInput" type="text"  name="userName"/>
                  </span>
                </div>
              </div>
              <div class="input-group">
                <div class="box">
                  <span>
                    <input placeholder="Email" class="myInput" type="text" name="email" />
                  </span>
                </div>
              </div>
              <div class="input-group">
                <div class="box">
                  <span>
                    <input id="password" placeholder="Password" class="myInput" type="password" name="password" />
                  </span>
                </div>
              </div>
              <div class="input-group">
                <div class="box">
                  <span>
                    <input id="confirmPassword" placeholder="Confirm password" class="myInput" type="password" name="confpassword" />
                  </span>
                </div>
              </div>
              </form>
              <div class="action-button">
                <button onclick="validateForm()">Register</button>
              </div>



              <div class="action-button">
                <button onclick="redirectToPage()">Back to Login</button>
              </div>

            </div>
          </div>

        </div>

      </div>
    </section>

    <script>
      function redirectToPage() {
        window.location.href = "index.jsp";
      }

      function validateForm() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;

        if (password !== confirmPassword) {
          alert("Password and Confirm Password do not match.");
          document.getElementById("password").value = "";
          document.getElementById("confirmPassword").value = "";

          // Focus on the password field
          document.getElementById("password").focus();
        }else {
          alert("Registered Successfully...");
          document.getElementById("myForm").submit();
        }
      }
    </script>
</body>
</html>
