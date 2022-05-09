module.exports = {
    devtool: 'source-map',
    output: {
        filename: 'react-app.js'
    },
    module: {
        rules: [
        {
                test: /\.css$/i,
                use: ["style-loader", "css-loader"],
              },{
            test: /\.(js|jsx)$/,
            exclude: /node_modules/,
            loader: "babel-loader",
            options: {
                presets: ['@babel/preset-env', '@babel/preset-react']
            }
        },
               {
                 test: /\.(png|svg|jpe?g|gif)$/,
                 include: /images/,
                 use: [
                   {
                     loader: 'file-loader',
                     options: {
                       name: '[name].[ext]',
                       outputPath: 'images/',
                       publicPath: 'images/'
                     }
                   }
                 ]
               },]
    },
    resolve: {
        extensions: ['.js', '.jsx']
    }
};