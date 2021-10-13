import './App.css';
import { Component } from 'react';
import Header from './components/header/header.component';

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
      </div>
    );
  }
}

export default App;
