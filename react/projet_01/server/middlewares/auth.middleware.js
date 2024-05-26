const jwt = require('jsonwebtoken');
const userModel = require("../models/user.model");

module.exports.checkToken = (req, res, next) => 
{
    const token = req.cookies.jwt;
    if (token) {
        jwt.verify(token, process.env.JWT_SECRET, (err, decodedToken) => {
            if (err) {
                console.log(err.message);
                res.status(401).json({
                    message: 'Unauthorized'
                });
            } else
            {
                console.log("decodedToken", decodedToken);
                next();
            }

        })
    } else {
        res.status(401).json({
            message: 'Unauthorized ! No token provided'
        });

    }
}


module.exports.isLoggedIn = (req, res, next) => {
    const token = req.cookies.jwt;
    if (token) {
        jwt.verify(token, process.env.JWT_SECRET, async (err, decoded) => {
            if (err) {
                res.locals.user = undefined;
                console.log("Cookie not valid !");
                // res.cookie('jwt', '', { maxAge: 1 });
                res.clearCookie();
                next();
            } else {
                let user = await userModel.findOne({email:decoded.email});
                console.log("Cookie is valid ! ", user);
                res.locals.user = {
                    username: user.username,
                    email: user.email,
                    collection: user.collection
                }
                next();
            }
        })

    } else {
        res.locals.user = undefined;
        console.log("No cookies ! ");
        next();
    }


}