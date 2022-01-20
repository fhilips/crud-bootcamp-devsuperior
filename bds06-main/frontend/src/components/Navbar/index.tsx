import './styles.css';
import 'bootstrap/js/src/collapse.js';

import { Link, NavLink } from 'react-router-dom';
import { useEffect } from 'react';
import history from 'util/history';
import { useContext } from 'react';
import { AuthContext } from 'AuthContext';
import { getTokenData, isAuthenticated } from 'util/auth';
import { removeAuthData } from 'util/storage';

const Navbar = () => {

  const { authContextData, setAuthContextData } = useContext(AuthContext);

  useEffect(() => {
    if (isAuthenticated()) {
      setAuthContextData({
        authenticated: true,
        tokenData: getTokenData(),
      });
    } else {
      setAuthContextData({
        authenticated: false,
      });
    }
  }, [setAuthContextData]);

  const handleLogoutClick = (event: React.MouseEvent<HTMLAnchorElement>) => {
    event.preventDefault();
    removeAuthData();
    setAuthContextData({
      authenticated: false,
    });
    history.replace('/');
  };

  return (
    <nav className="navbar navbar-expand-md navbar-dark bg-primary main-nav">
      <div className="container-fluid">
        <Link to="/" className="nav-logo-text">
          <h4>MovieFlix</h4>
        </Link>
          <div className="nav-login-logout">
          {authContextData.authenticated ? (
              <a className="botton-logout" href="#logout" onClick={handleLogoutClick}>
                SAIR
              </a>
          ) : (<a href="#logout"></a>)}
           
          
          
          
        </div>
      </div>
    </nav>
  );
};

export default Navbar;