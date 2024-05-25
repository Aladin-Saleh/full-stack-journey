const userModel = require('../models/user.model');
const jwt = require('jsonwebtoken');

const { registerErrors } = require('../utils/errors.utils');

// maxAge d'une valeur de 72h (3600000*24*7)
const maxAge = 3600000 * 24 * 7;

const createToken = (email) => {
  return jwt.sign({ email }, process.env.JWT_SECRET, { expiresIn: "72h" });
};


module.exports.register = async (req, res) => {

    const { username, email, password } = req.body;

    try {
        
        const user = await userModel.create({ username, email, password });
        res.status(201).json({
            message: 'User created successfully',
            user: {
                username: user.username,
                email: user.email
            }
        });

    } catch (err) {
    
        const error = registerErrors(err);

        res.status(error.code).json({
            message: 'An error occured',
            reason: error.errors
        });
    }


}

module.exports.login = async (req, res) => {

    const { email, password } = req.body;

    try {
        
        const user = await userModel.login(email, password);
        const token = createToken(user.email);

        res.cookie('jwt', token, { httpOnly: true, maxAge });
        res.status(200).json({
            message: 'User logged in successfully',
            user: {
                username: user.username,
                email: user.email
            }
        });

    } catch (err) {

        res.status(400).json({
            message: 'An error occured',
            reason: err.message
        });
    }

}   

module.exports.logout = async (req, res) => {

    res.cookie('jwt', '', { maxAge: 1 });
    res.status(200).json({
        message: 'User logged out successfully'
    });


}
