import './App.css';
import { Component } from 'react';
import Header from './components/header/header.component';
import Body from './components/body/body.component';
import Footer from './components/footer/footer.component';

class App extends Component {
  async componentDidMount() {
    const response = await fetch('/admin/api/words');
    const body = await response.json();
    console.log(body);
  }

  render() {
    return (
      <div className="wrapper">
        <Header></Header>
        <Body></Body>
        <Footer></Footer>
      </div>
    );
  }
}

export default App;
