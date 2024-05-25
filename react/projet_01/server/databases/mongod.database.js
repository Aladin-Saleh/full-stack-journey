const dotenv    = require('dotenv').config({path:'./configuration/.env'});
const mongoose  = require('mongoose');

mongoose.connect(`mongodb://${process.env.DB_HOST_MONGO}:27017/`,{
    
    // auth: {
    //     username: process.env.DB_USER_MONGO,
    //     password: process.env.DB_PASS_MONGO
    // },
    dbName: "gamesdb",

    useNewUrlParser: true,
    useUnifiedTopology: true,
})
.then(() => console.log('Connexion à MongoDB réussie !'))
.catch((err) => console.log('Connexion à MongoDB échouée !', err));