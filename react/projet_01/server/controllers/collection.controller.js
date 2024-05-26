const userModel = require('../models/user.model');
const jwt = require('jsonwebtoken');
const objectId = require('mongoose').Types.ObjectId;



module.exports.addGame = async (req, res) => {
    const { gameInfo } = req.body;
    const token = req.cookies.jwt;

    if (!token) {
        return res.status(401).send({
            message: 'Unauthorized access ! Please log in.'
        });
    }

    if (!gameInfo) {
        return res.status(400).send({
            message: 'Missing game information'
        });
    }

    try {
        const decodedToken = jwt.verify(token, process.env.JWT_SECRET);
        const email = decodedToken.email;

        const user = await userModel.findOneAndUpdate({ email }, {
            $push: {
                collection: {
                    gameInfo,
                    gameStatus: 'Not started',
                    gameRating: 0,
                    gameReview: ''
                }
            }
        }, { new: true });

        res.status(200).json(user);

    } catch (error) {
        if (error instanceof jwt.JsonWebTokenError) {
            return res.status(403).send({
                message: 'Invalid token'
            });
        }
        res.status(500).send('Internal server error');
    }
};


module.exports.getCollection = async (req, res) => {

    const token = req.cookies.jwt;

    if (!token) {
        return res.status(401).send({
            message: 'Unauthorized access ! Please log in.'
        });
    }

    try {
        const decodedToken = jwt.verify(token, process.env.JWT_SECRET);
        const email = decodedToken.email;

        const user = await userModel.findOne({ email});
        res.status(200).json({
            message: 'Collection retrieved',
            collection: user.collection
        });
    } catch (error) {
        if (error instanceof jwt.JsonWebTokenError) {
            return res.status(403).send({
                message: 'Invalid token'
            });
        }
        res.status(500).send({
            message: 'Internal server error'
        });
    }

}

module.exports.deleteGame = async (req, res) => {
    const { id } = req.params;
    const token = req.cookies.jwt;

    if(!objectId.isValid(id)){
        return res.status(400).json({
            message: 'ID non valide ' + id,
        });
    }

    if (!token) {
        return res.status(401).send({
            message: 'Unauthorized access ! Please log in.'
        });
    }

    if (!id) {
        return res.status(400).send({
            message: 'Missing game id'
        });
    }

    try {
        const decodedToken = jwt.verify(token, process.env.JWT_SECRET);
        const email = decodedToken.email;
        
        const game = await userModel.findOne({email, 'collection._id': id});
        if (!game) {
            return res.status(400).send({
                message: 'Game not found'
            });
        }

        const user = await userModel.findOneAndUpdate({ email }, {
            $pull: {
                collection: {
                    _id: id
                }
            }
        }, { new: true });
    

        res.status(200).json(user);
    } catch (error) {
        console.log(error);
        if (error instanceof jwt.JsonWebTokenError) {
            return res.status(403).send({
                message: 'Invalid token'
            });
        }
        res.status(500).send('Internal server error');
    }
}

module.exports.updateGame = async (req, res) => {
    const token = req.cookies.jwt;
    const { id } = req.params;
    const { gameStatus, gameRating, gameReview } = req.body;

    if(!objectId.isValid(id)){
        return res.status(400).json({
            message: 'ID non valide ' + id,
        });
    }



    if (!token) {
        return res.status(401).send({
            message: 'Unauthorized access ! Please log in.'
        });
    }

    if (!id) {
        return res.status(400).send({
            message: 'Missing game id'
        });
    }

    if (!gameStatus && !gameRating && !gameReview) {
        return res.status(400).send({
            message: 'Missing game information'
        });
    }

    try {


        const decodedToken = jwt.verify(token, process.env.JWT_SECRET);
        const email = decodedToken.email;

        // check if the id exists in the collection
        const game = await userModel.findOne({email, 'collection._id': id});
        if (!game) {
            return res.status(400).send({
                message: 'Game not found'
            });
        }


        const user = await userModel.findOneAndUpdate({email,'collection._id': id}, 
        {
            $set: {
                'collection.$.gameStatus': gameStatus,
                'collection.$.gameRating': gameRating,
                'collection.$.gameReview': gameReview
            }
        }, { new: true });

        res.status(200).json({
            message: 'Game updated',
            collection: user.collection
        });

    } catch (error) {

        console.log(error);

        if (error instanceof jwt.JsonWebTokenError) {
            return res.status(403).send({
                message: 'Invalid token'
            });
        }
        res.status(500).send({
            message: 'Internal server error'
        });
    }



}