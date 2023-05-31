<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Finance Management System - Login</title>
  <link rel="stylesheet" href="css/loginForm.css">
</head>
<body>
<%--<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">--%>
  <section>

    <div class="main-form-container">
      <div id="form_section" class="form-container">
        <div class="login-form form-wraper ">
          <div>
            <div class="form-title">
              <h2>Login</h2>
            </div>
            <form action="Login" method="post">
              <div class="input-group">
                <div class="box">
                  <span>
                    <input placeholder="User name" class="myInput" type="text"  name="userName"/>
                  </span>
                </div>
              </div>
              <div class="input-group">
                <div class="box">
                  <span>
                    <input placeholder="Password" class="myInput" type="password" name="password" />
                  </span>
                </div>
              </div>

              <div class="forget-password">
                <a href="">FORGOT PASSWORD</a>
              </div>

              <div class="action-button">
                <button>Login</button>
              </div>

              <% String errorMessage = (String) request.getAttribute("status");
                if (errorMessage != null && errorMessage.equals("fail")) { %>
              <script>
                alert("Incorrect username or password");
              </script>
              <% } %>
            </form>
            <div class="action-button">
              <button onclick="redirectToPage()">Register</button>
            </div>
          </div>
        </div>

      </div>

    </div>
  </section>
<script>
  function redirectToPage() {
    window.location.href = "register.jsp";
  }
</script>
</body>
</html>