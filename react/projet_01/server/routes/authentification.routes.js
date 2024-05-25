const router            = require('express').Router();
const authController    = require('../controllers/authentification.controller');

// Register 
router.post('/register', authController.register);

// Login
router.post('/login', authController.login);

// Logout
router.get('/logout', authController.logout);





module.exports = router;