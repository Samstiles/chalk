var express = require('express'),
    bodyParser = require('body-parser'),
    methodOverride = require('method-override'),
    morgan = require('morgan'),
    restful = require('node-restful'),
    mongoose = restful.mongoose;
var app = express();

app.use(morgan('dev'));
app.use(bodyParser.urlencoded({'extended':'true'}));
app.use(bodyParser.json());
app.use(bodyParser.json({type:'application/vnd.api+json'}));
app.use(methodOverride());

mongoose.connect("mongodb://climbing:climbing@ds023912.mlab.com:23912/climbing-dev");

var Like = app.like = restful.model('like', mongoose.Schema({
  comment: {type: String, ref: 'comment'},
  liker: {type: String, ref: 'user'}
}))
.methods(['get', 'post', 'put', 'delete']);

var Comment = app.comment = restful.model('comment', mongoose.Schema({
  content: String,
  likes: [{type: String, ref: 'like'}],
  route: {type: String, ref: 'route'},
  submitter: {type: String, ref: 'user'}
}))
.methods(['get', 'post', 'put', 'delete']);

var Route = app.climbing_route = restful.model('route', mongoose.Schema({
  name: String,
  submitter: {type: String, ref: 'user'},
  ratings: [{type: String, ref: 'rating'}],
  comments: [{type: String, ref: 'comment'}]
}))
.methods(['get', 'post', 'put', 'delete']);

var Send = app.send = restful.model('send', mongoose.Schema({
  sender: {type: String, ref: 'user'},
  route: {type: String, ref: 'route'},
  feedback: String
}))
.methods(['get', 'post', 'put', 'delete']);

var Rating = app.rating = restful.model('rating', mongoose.Schema({
  rater: {type: String, ref: 'user'},
  route: {type: String, ref: 'route'},
  rating: Number,
  feedback: String
}))
.methods(['get', 'post', 'put', 'delete']);

var User = app.resource = restful.model('user', mongoose.Schema({
    firstName: String,
    lastName: String,
    email: String,
    comments: [{type: String, ref: 'comment'}],
    routes: [{type: String, ref: 'route'}],
    sends: [{type: String, ref: 'send'}],
    ratings: [{type: String, ref: 'rating'}]
}))
.methods(['get', 'post', 'put', 'delete']);

User.register(app, '/users');
Rating.register(app, '/ratings');
Send.register(app, '/sends');
Route.register(app, '/routes');
Comment.register(app, '/comments');


console.log('Server listening on port 3000...');

app.listen(3000);
