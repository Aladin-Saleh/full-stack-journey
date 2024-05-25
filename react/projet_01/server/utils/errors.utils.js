module.exports.registerErrors = (err) => {
    let errors = { username: '', email: '', password: '' };
    let code = 500;

    // Username
    if (err.message.includes('`username` is required')) {
        errors.username = 'Username is required, please provide a username';
        code = 400;
    }

    if (err.message.includes('is shorter than the minimum allowed length (3)')) {
        errors.username = 'Username must be at least 3 characters long';
        code = 400;
    }

    if (err.code === 11000 && Object.keys(err.keyValue)[0].includes('username')) {
        errors.username = 'This username is already taken';
        code = 400;
    }


    // Password
    if (err.message.includes('`password` is required')) {
        errors.password = 'password is required, please provide a password';
        code = 400;
    }

    if (err.message.includes('is shorter than the minimum allowed length (5)')) {
        errors.password = 'Password must be at least 5 characters long';
        code = 400;
    }

    // Email
    if (err.message.includes('`email` is required')) {
        errors.email = 'Email is required, please provide an email';
        code = 400;
    }

    if (err.message.includes('invalid email')) {
        errors.email = 'Invalid email, please provide a valid email';
        code = 400;
    }

    if (err.code === 11000 && Object.keys(err.keyValue)[0].includes('email')) {
        errors.email = 'This email is already taken';
        code = 400;
    }

    return {
        errors,
        code
    }


}


