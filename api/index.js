var express = require('express'),
    bodyParser = require('body-parser'),
    methodOverride = require('method-override'),
    morgan = require('morgan'),
    restful = require('node-restful'),
    cors = require('cors'),
    bcrypt = require('bcrypt'),
    jwt = require('jsonwebtoken'),
    mongoose = restful.mongoose,
    uniqueValidator = require('mongoose-unique-validator');
var app = express();
var port = 3450;
var JWT_SECRET = process.env.JWT_SECRET;
var DB_SECRET = process.env.DB_SECRET;
var SALT_ROUNDS = parseInt(process.env.SALT_ROUNDS);
var MONGO_URL = process.env.MONGO_URL;
app.use(morgan('dev'));
app.use(cors());
app.use(bodyParser.urlencoded({'extended':'true'}));
app.use(bodyParser.json());
app.use(bodyParser.json({type:'application/vnd.api+json'}));
app.use(methodOverride());
mongoose.connect(MONGO_URL);

if (!JWT_SECRET ||
    !DB_SECRET ||
    !SALT_ROUNDS ||
    !MONGO_URL) {
  console.log('!! ERROR !! Missing mandatory environment variables for API startup.');
  process.exit();
}

// -- User routes

var userSchema = mongoose.Schema({
  email: {
    type: String,
    unique: true,
    uniqueCaseInsensitive: true,
    required: [true, 'Email is a required field'] },
  password: {
    type: String,
    minlength: [8, 'Password must be between 8 and 256 characters long.'],
    maxlength: [256, 'Password must be between 8 and 256 characters long.'],
    required: [true, 'Password is a required field'] },
  firstName: {
    type: String,
    required: [true, 'First name is a required field'] },
  lastName: {
    type: String,
    required: [true, 'Last name is a required field'] }
});

userSchema.plugin(uniqueValidator, { message: "A user with that email address is already registered." });

var User = app.user = restful.model('user', userSchema).methods(['get', 'post', 'put']);

User.before('post', function(req, res, next) {
  if (req.body.password && req.body.password.length > 7) {
    bcrypt.genSalt(SALT_ROUNDS, function(err, salt) {
      if (err) next('Failed to generate salt.')

      bcrypt.hash(req.body.password, salt, function(err, hash) {
        if (err) next('Failed to hash password');

        req.body.password = hash;
        next();
      });
    });
  } else {
    next();
  }
});

User.after('post', function(req, res, next) {
  if (res.locals.status_code === 201) {
    var token = jwt.sign({ id: res.locals.bundle._id }, JWT_SECRET, {}, function(err, token) {
      if (err || !token) return res.status(500).send('Error generating access token.');

      res.locals.bundle = {
        userInfo: {
          id: res.locals.bundle._id,
          email: res.locals.bundle.email,
          firstName: res.locals.bundle.firstName,
          lastName: res.locals.bundle.lastName,
        },
        auth: {
          token: token
        }
      };

      return next();
    });
  } else {
    return next();
  }
});

User.route('login.post', function(req, res, next) {
  if (!req.body.password || !req.body.email) return res.status(400).send('Missing email or password.');

  User.findOne({ email: req.body.email }, function(err, person) {
    if (err || !person) return res.status(404).send('No user found with that email address.');

    bcrypt.compare(req.body.password, person.password, function(err, result) {
      if (err || result === false) return res.status(400).send('Invalid credentials.');

      var token = jwt.sign({ id: person._id }, JWT_SECRET, {}, function(err, token) {
        if (err || !token) return res.status(500).send('Error generating access token.');

        var loginSuccess = {
          userInfo: {
            email: person.email,
            firstName: person.firstName,
            lastName: person.lastName,
            id: person._id
          },
          auth: {
            token: token
          }
        };

        return res.status(200).send(loginSuccess);
      });
    });
  });
});

// User.route('butts.post', function(req, res, next) {
//   if (!req.body.token) return res.status(400).send('Missing token');
//
//   jwt.verify(req.body.token, JWT_SECRET, {}, function(err, payload) {
//     if (err) return res.status(500).send('Error parsing JWT payload');
//     console.log(payload);
//     res.sendStatus(200);
//   });
// });

User.register(app, '/users');

app.listen(port);

console.log('App listening on *:' + port);
