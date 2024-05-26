const router = require('express').Router();
const collectionController = require('../controllers/collection.controller');


// Add game to collection
router.post('/add', collectionController.addGame);


// Get my collection of games
router.get('/', collectionController.getCollection);


// Delete a game from my collection
router.delete('/delete/:id', collectionController.deleteGame);


// Update a game from my collection
router.put('/update/:id', collectionController.updateGame);





module.exports = router;