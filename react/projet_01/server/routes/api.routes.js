const router = require('express').Router();
const apiController = require('../controllers/api.controller');


// Get a list of games
router.get('/games', apiController.getGames);


// Get a list of games by name
router.get('/games/:name', apiController.getGamesByName);




module.exports = router;