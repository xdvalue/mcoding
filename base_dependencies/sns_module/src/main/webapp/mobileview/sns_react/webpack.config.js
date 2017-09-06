var webpack = require('webpack');
var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = {
	// entry: ['webpack-dev-server/client?http://localhost:3000',
	// 	'webpack/hot/only-dev-server',
	// 	'./src/js/index.js'
	// ],
	entry: ['./src/js/index.js'],
	module: {
		loaders: [
		    { test: /\.jsx?$/, include: path.join(__dirname, "src"), loader: 'react-hot' }, 
		    { test: /\.jsx?$/, include: path.join(__dirname, "src"), exclude: /node_modules/, loader: 'babel', query: {presets: ['react', 'es2015']}}, 
		    { test: /\.css$/, include: path.join(__dirname, "src/css/"), loader: "style-loader!css-loader" },
		    { test: /\.(jpg|png|eot|svg|ttf|woff)$/, loader: "url-loader?limit=8192&name=res/[hash].[ext]" }
		]
	},
	resolve: {
		extensions: ["", ".js", ".jsx"]
	},
	externals:{
		'moment': 'moment',
		'react':'React',
		'immutable': 'Immutable',
		'redux':'Redux',
		'react':'React',
		'react-dom':'ReactDOM',
		'react-router':'ReactRouter',
		'react-redux':'ReactRedux',
		'redux-thunk':'ReduxThunk.default'
	},
	output: {
		path: __dirname + "/assets",
		filename: "js/[hash].js"
	},
	devServer: {
		contentBase: "./assets/",
		hot: true,
		host: "localhost",
	    proxy: {
            '/sns_module/*': 'http://localhost:18080'
        }
	},
	plugins: [
	    new webpack.optimize.UglifyJsPlugin({
	    	compress: {
	    		warnings: false
	    	}
	    }),
		// new webpack.HotModuleReplacementPlugin(),
		// new webpack.NoErrorsPlugin(),
		new webpack.ContextReplacementPlugin(/moment[\/\\]locale$/, /zh-cn/),
		new HtmlWebpackPlugin({
			template: 'src/index.html',
			filename: 'index.html'
		})
	    
	]

}