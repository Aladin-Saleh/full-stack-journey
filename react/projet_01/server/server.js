const dotenv    = require('dotenv').config({path:'./configuration/.env'});

const express   = require('express');
const app       = express();


const bodyParser = require('body-parser');
const cookieParser = require('cookie-parser');

const PORT = process.env.PORT || 3080;

require('./databases/mongod.database');
const { checkToken, isLoggedIn } = require('./middlewares/auth.middleware'); 


const authRoutes    = require('./routes/authentification.routes');
const apiRoutes     = require('./routes/api.routes');
const collRoutes    = require('./routes/collection.routes');




app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(cookieParser());


// Cors
app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', 'http://localhost:3000'); // Permet seulement à l'application http://localhost:3000 de communiquer avec l'API
    res.header('Access-Control-Allow-Methods', 'POST, GET, PUT, DELETE, OPTIONS');
    res.header('Access-Control-Allow-Credentials', 'true');
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept, Authorization');
    next();
});

app.get("/jwtid", isLoggedIn, (req, res) => {
    res.status(200).json({
      message: "Connexion réussie",
      user: res.locals.user,
    });
});



app.get('/api',checkToken, (req, res) => {
    res.json({ message: 'Welcome to the API' });
});

app.use('/auth', authRoutes);
app.use('/ext', checkToken, apiRoutes);
app.use('/collection', checkToken, collRoutes);


app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
    app._router.stack.map(r => r.route).filter(r => r).map(r => r.path).join(', ').split(', ').forEach(route => {
        console.log(`http://localhost:${PORT}${route}`);
    });
});
