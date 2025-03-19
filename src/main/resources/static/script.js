const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirmPassword');
const passwordError = document.getElementById('passwordError');
const resetButton = document.getElementById('resetButton');

function validatePasswords() {
    if (passwordInput.value.length < 6 || confirmPasswordInput.value.length < 6) {
        passwordError.textContent = "Password must be at least 6 characters long.";
        passwordError.style.display = 'block';
        resetButton.disabled = true;
    } else if (passwordInput.value !== confirmPasswordInput.value) {
        passwordError.textContent = "Passwords do not match!";
        passwordError.style.display = 'block';
        resetButton.disabled = true;
    } else {
        passwordError.style.display = 'none';
        resetButton.disabled = false;
    }
}

passwordInput.addEventListener('input', validatePasswords);
confirmPasswordInput.addEventListener('input', validatePasswords);

function resetPassword() {
    const token = document.getElementById('token').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    const path = window.location.pathname;
    const endpoint = path.includes('/lootgenerator/') ? '/lootgenerator/update-password' : '/update-password';

    fetch(endpoint, {
        method: 'POST',
        referrerPolicy: "unsafe-url",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            token: token,
            newPassword: password
        })
    })
        .then(response => {
            if (response.ok) {
                document.getElementById('resetForm').style.display = 'none';
                document.getElementById('successMessage').style.display = 'block';
                document.getElementById('resetTitle').style.display = 'none';
                document.title = "Password Reset Successful";
                history.replaceState({}, "Password Reset Successful", window.location.pathname);
            } else {
                response.text().then(errorMessage => {
                    passwordError.textContent = "Password reset failed!";
                    passwordError.style.display = 'block';
                });
            }
        });
}