const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');
const validator = require('validator');

const isEmail = validator.isEmail; // Valide l'email (format)

const userSchema = new mongoose.Schema({


    username: {
        type: String,
        required: true,
        unique: true,
        trim: true,
        minlength: 3,
        maxlength: 16
    },
    
    password: {
        type: String,
        required: true,
        minlength: 5,
        maxlength: 1024
    },

    email: {
        type: String,
        required: true,
        validate: [isEmail, 'invalid email'],
        unique: true,
        trim: true
    },

    collection: {
        type: Array,
        default: [] 
    }


}, { timestamps: true });



// Hachage du mot de passe avant de sauvegarder l'utilisateur dans la base de données
userSchema.pre('save', async function (next) {
    const salt = await bcrypt.genSalt(10);
    this.password = await bcrypt.hash(this.password, salt);
    next(); // Passe à l'étape suivante
})


// Méthode de connexion de l'utilisateur
userSchema.statics.login = async function (email, password) {
    const user = await this.findOne({ email });
    if (user) {
        const auth = await bcrypt.compare(password, user.password);
        if (auth) {
            return user;
        }
        throw Error('Incorrect password');
    }
    throw Error('Incorrect email');
}



module.exports = mongoose.model('User', userSchema);
